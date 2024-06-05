package cdm.event.position;

import cdm.event.common.Lineage;
import cdm.event.position.PortfolioState;
import cdm.event.position.PortfolioState.PortfolioStateBuilder;
import cdm.event.position.PortfolioState.PortfolioStateBuilderImpl;
import cdm.event.position.PortfolioState.PortfolioStateImpl;
import cdm.event.position.Position;
import cdm.event.position.meta.PortfolioStateMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * State-full representation of a Portfolio that describes all the positions held at a given time, in various states which can be either traded, settled, etc., with lineage information to the previous state
 * @version ${project.version}
 */
@RosettaDataType(value="PortfolioState", builder=PortfolioState.PortfolioStateBuilderImpl.class, version="${project.version}")
public interface PortfolioState extends RosettaModelObject, GlobalKey {

	PortfolioStateMeta metaData = new PortfolioStateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The list of positions, each containing a Quantity and a Product.
	 */
	List<? extends Position> getPositions();
	/**
	 * Pointer to the previous PortfolioState and new Event(s) leading to the current (new) state. Previous PortfolioState in the Lineage can be Null in case this is the start of the chain of Events.
	 */
	Lineage getLineage();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PortfolioState build();
	
	PortfolioState.PortfolioStateBuilder toBuilder();
	
	static PortfolioState.PortfolioStateBuilder builder() {
		return new PortfolioState.PortfolioStateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PortfolioState> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PortfolioState> getType() {
		return PortfolioState.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("positions"), processor, Position.class, getPositions());
		processRosetta(path.newSubPath("lineage"), processor, Lineage.class, getLineage());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PortfolioStateBuilder extends PortfolioState, RosettaModelObjectBuilder {
		Position.PositionBuilder getOrCreatePositions(int _index);
		List<? extends Position.PositionBuilder> getPositions();
		Lineage.LineageBuilder getOrCreateLineage();
		Lineage.LineageBuilder getLineage();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PortfolioState.PortfolioStateBuilder addPositions(Position positions0);
		PortfolioState.PortfolioStateBuilder addPositions(Position positions1, int _idx);
		PortfolioState.PortfolioStateBuilder addPositions(List<? extends Position> positions2);
		PortfolioState.PortfolioStateBuilder setPositions(List<? extends Position> positions3);
		PortfolioState.PortfolioStateBuilder setLineage(Lineage lineage);
		PortfolioState.PortfolioStateBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("positions"), processor, Position.PositionBuilder.class, getPositions());
			processRosetta(path.newSubPath("lineage"), processor, Lineage.LineageBuilder.class, getLineage());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PortfolioState.PortfolioStateBuilder prune();
	}

	/*********************** Immutable Implementation of PortfolioState  ***********************/
	class PortfolioStateImpl implements PortfolioState {
		private final List<? extends Position> positions;
		private final Lineage lineage;
		private final MetaFields meta;
		
		protected PortfolioStateImpl(PortfolioState.PortfolioStateBuilder builder) {
			this.positions = ofNullable(builder.getPositions()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.lineage = ofNullable(builder.getLineage()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("positions")
		public List<? extends Position> getPositions() {
			return positions;
		}
		
		@Override
		@RosettaAttribute("lineage")
		public Lineage getLineage() {
			return lineage;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PortfolioState build() {
			return this;
		}
		
		@Override
		public PortfolioState.PortfolioStateBuilder toBuilder() {
			PortfolioState.PortfolioStateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PortfolioState.PortfolioStateBuilder builder) {
			ofNullable(getPositions()).ifPresent(builder::setPositions);
			ofNullable(getLineage()).ifPresent(builder::setLineage);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PortfolioState _that = getType().cast(o);
		
			if (!ListEquals.listEquals(positions, _that.getPositions())) return false;
			if (!Objects.equals(lineage, _that.getLineage())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (positions != null ? positions.hashCode() : 0);
			_result = 31 * _result + (lineage != null ? lineage.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PortfolioState {" +
				"positions=" + this.positions + ", " +
				"lineage=" + this.lineage + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PortfolioState  ***********************/
	class PortfolioStateBuilderImpl implements PortfolioState.PortfolioStateBuilder, GlobalKeyBuilder {
	
		protected List<Position.PositionBuilder> positions = new ArrayList<>();
		protected Lineage.LineageBuilder lineage;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PortfolioStateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("positions")
		public List<? extends Position.PositionBuilder> getPositions() {
			return positions;
		}
		
		public Position.PositionBuilder getOrCreatePositions(int _index) {
		
			if (positions==null) {
				this.positions = new ArrayList<>();
			}
			Position.PositionBuilder result;
			return getIndex(positions, _index, () -> {
						Position.PositionBuilder newPositions = Position.builder();
						return newPositions;
					});
		}
		
		@Override
		@RosettaAttribute("lineage")
		public Lineage.LineageBuilder getLineage() {
			return lineage;
		}
		
		@Override
		public Lineage.LineageBuilder getOrCreateLineage() {
			Lineage.LineageBuilder result;
			if (lineage!=null) {
				result = lineage;
			}
			else {
				result = lineage = Lineage.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		public PortfolioState.PortfolioStateBuilder addPositions(Position positions) {
			if (positions!=null) this.positions.add(positions.toBuilder());
			return this;
		}
		
		@Override
		public PortfolioState.PortfolioStateBuilder addPositions(Position positions, int _idx) {
			getIndex(this.positions, _idx, () -> positions.toBuilder());
			return this;
		}
		@Override 
		public PortfolioState.PortfolioStateBuilder addPositions(List<? extends Position> positionss) {
			if (positionss != null) {
				for (Position toAdd : positionss) {
					this.positions.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("positions")
		public PortfolioState.PortfolioStateBuilder setPositions(List<? extends Position> positionss) {
			if (positionss == null)  {
				this.positions = new ArrayList<>();
			}
			else {
				this.positions = positionss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("lineage")
		public PortfolioState.PortfolioStateBuilder setLineage(Lineage lineage) {
			this.lineage = lineage==null?null:lineage.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PortfolioState.PortfolioStateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PortfolioState build() {
			return new PortfolioState.PortfolioStateImpl(this);
		}
		
		@Override
		public PortfolioState.PortfolioStateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PortfolioState.PortfolioStateBuilder prune() {
			positions = positions.stream().filter(b->b!=null).<Position.PositionBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (lineage!=null && !lineage.prune().hasData()) lineage = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPositions()!=null && getPositions().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getLineage()!=null && getLineage().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PortfolioState.PortfolioStateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PortfolioState.PortfolioStateBuilder o = (PortfolioState.PortfolioStateBuilder) other;
			
			merger.mergeRosetta(getPositions(), o.getPositions(), this::getOrCreatePositions);
			merger.mergeRosetta(getLineage(), o.getLineage(), this::setLineage);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PortfolioState _that = getType().cast(o);
		
			if (!ListEquals.listEquals(positions, _that.getPositions())) return false;
			if (!Objects.equals(lineage, _that.getLineage())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (positions != null ? positions.hashCode() : 0);
			_result = 31 * _result + (lineage != null ? lineage.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PortfolioStateBuilder {" +
				"positions=" + this.positions + ", " +
				"lineage=" + this.lineage + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
