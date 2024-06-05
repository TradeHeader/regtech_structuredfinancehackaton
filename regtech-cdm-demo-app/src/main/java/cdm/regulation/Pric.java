package cdm.regulation;

import cdm.regulation.Pric;
import cdm.regulation.Pric.PricBuilder;
import cdm.regulation.Pric.PricBuilderImpl;
import cdm.regulation.Pric.PricImpl;
import cdm.regulation.meta.PricMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="Pric", builder=Pric.PricBuilderImpl.class, version="${project.version}")
public interface Pric extends RosettaModelObject {

	PricMeta metaData = new PricMeta();

	/*********************** Getter Methods  ***********************/
	Pric getPric();
	String getBsisPts();

	/*********************** Build Methods  ***********************/
	Pric build();
	
	Pric.PricBuilder toBuilder();
	
	static Pric.PricBuilder builder() {
		return new Pric.PricBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Pric> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Pric> getType() {
		return Pric.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("pric"), processor, Pric.class, getPric());
		processor.processBasic(path.newSubPath("bsisPts"), String.class, getBsisPts(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PricBuilder extends Pric, RosettaModelObjectBuilder {
		Pric.PricBuilder getOrCreatePric();
		Pric.PricBuilder getPric();
		Pric.PricBuilder setPric(Pric pric);
		Pric.PricBuilder setBsisPts(String bsisPts);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("pric"), processor, Pric.PricBuilder.class, getPric());
			processor.processBasic(path.newSubPath("bsisPts"), String.class, getBsisPts(), this);
		}
		

		Pric.PricBuilder prune();
	}

	/*********************** Immutable Implementation of Pric  ***********************/
	class PricImpl implements Pric {
		private final Pric pric;
		private final String bsisPts;
		
		protected PricImpl(Pric.PricBuilder builder) {
			this.pric = ofNullable(builder.getPric()).map(f->f.build()).orElse(null);
			this.bsisPts = builder.getBsisPts();
		}
		
		@Override
		@RosettaAttribute("pric")
		public Pric getPric() {
			return pric;
		}
		
		@Override
		@RosettaAttribute("bsisPts")
		public String getBsisPts() {
			return bsisPts;
		}
		
		@Override
		public Pric build() {
			return this;
		}
		
		@Override
		public Pric.PricBuilder toBuilder() {
			Pric.PricBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Pric.PricBuilder builder) {
			ofNullable(getPric()).ifPresent(builder::setPric);
			ofNullable(getBsisPts()).ifPresent(builder::setBsisPts);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Pric _that = getType().cast(o);
		
			if (!Objects.equals(pric, _that.getPric())) return false;
			if (!Objects.equals(bsisPts, _that.getBsisPts())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (pric != null ? pric.hashCode() : 0);
			_result = 31 * _result + (bsisPts != null ? bsisPts.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Pric {" +
				"pric=" + this.pric + ", " +
				"bsisPts=" + this.bsisPts +
			'}';
		}
	}

	/*********************** Builder Implementation of Pric  ***********************/
	class PricBuilderImpl implements Pric.PricBuilder {
	
		protected Pric.PricBuilder pric;
		protected String bsisPts;
	
		public PricBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("pric")
		public Pric.PricBuilder getPric() {
			return pric;
		}
		
		@Override
		public Pric.PricBuilder getOrCreatePric() {
			Pric.PricBuilder result;
			if (pric!=null) {
				result = pric;
			}
			else {
				result = pric = Pric.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("bsisPts")
		public String getBsisPts() {
			return bsisPts;
		}
		
	
		@Override
		@RosettaAttribute("pric")
		public Pric.PricBuilder setPric(Pric pric) {
			this.pric = pric==null?null:pric.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("bsisPts")
		public Pric.PricBuilder setBsisPts(String bsisPts) {
			this.bsisPts = bsisPts==null?null:bsisPts;
			return this;
		}
		
		@Override
		public Pric build() {
			return new Pric.PricImpl(this);
		}
		
		@Override
		public Pric.PricBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Pric.PricBuilder prune() {
			if (pric!=null && !pric.prune().hasData()) pric = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPric()!=null && getPric().hasData()) return true;
			if (getBsisPts()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Pric.PricBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Pric.PricBuilder o = (Pric.PricBuilder) other;
			
			merger.mergeRosetta(getPric(), o.getPric(), this::setPric);
			
			merger.mergeBasic(getBsisPts(), o.getBsisPts(), this::setBsisPts);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Pric _that = getType().cast(o);
		
			if (!Objects.equals(pric, _that.getPric())) return false;
			if (!Objects.equals(bsisPts, _that.getBsisPts())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (pric != null ? pric.hashCode() : 0);
			_result = 31 * _result + (bsisPts != null ? bsisPts.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PricBuilder {" +
				"pric=" + this.pric + ", " +
				"bsisPts=" + this.bsisPts +
			'}';
		}
	}
}
