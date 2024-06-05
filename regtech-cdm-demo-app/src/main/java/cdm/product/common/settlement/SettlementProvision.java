package cdm.product.common.settlement;

import cdm.product.common.settlement.SettlementProvision;
import cdm.product.common.settlement.SettlementProvision.SettlementProvisionBuilder;
import cdm.product.common.settlement.SettlementProvision.SettlementProvisionBuilderImpl;
import cdm.product.common.settlement.SettlementProvision.SettlementProvisionImpl;
import cdm.product.common.settlement.ShapingProvision;
import cdm.product.common.settlement.meta.SettlementProvisionMeta;
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
 * Defines parameters that regulate a settlement, for instance whether this settlement should be netted with other ones or broken-down into smaller amounts.
 * @version ${project.version}
 */
@RosettaDataType(value="SettlementProvision", builder=SettlementProvision.SettlementProvisionBuilderImpl.class, version="${project.version}")
public interface SettlementProvision extends RosettaModelObject {

	SettlementProvisionMeta metaData = new SettlementProvisionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines the parameters that are necessary to &#39;shape&#39; a settlement, i.e. break it down into smaller amounts.
	 */
	ShapingProvision getShapingProvisions();

	/*********************** Build Methods  ***********************/
	SettlementProvision build();
	
	SettlementProvision.SettlementProvisionBuilder toBuilder();
	
	static SettlementProvision.SettlementProvisionBuilder builder() {
		return new SettlementProvision.SettlementProvisionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SettlementProvision> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SettlementProvision> getType() {
		return SettlementProvision.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("shapingProvisions"), processor, ShapingProvision.class, getShapingProvisions());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SettlementProvisionBuilder extends SettlementProvision, RosettaModelObjectBuilder {
		ShapingProvision.ShapingProvisionBuilder getOrCreateShapingProvisions();
		ShapingProvision.ShapingProvisionBuilder getShapingProvisions();
		SettlementProvision.SettlementProvisionBuilder setShapingProvisions(ShapingProvision shapingProvisions);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("shapingProvisions"), processor, ShapingProvision.ShapingProvisionBuilder.class, getShapingProvisions());
		}
		

		SettlementProvision.SettlementProvisionBuilder prune();
	}

	/*********************** Immutable Implementation of SettlementProvision  ***********************/
	class SettlementProvisionImpl implements SettlementProvision {
		private final ShapingProvision shapingProvisions;
		
		protected SettlementProvisionImpl(SettlementProvision.SettlementProvisionBuilder builder) {
			this.shapingProvisions = ofNullable(builder.getShapingProvisions()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("shapingProvisions")
		public ShapingProvision getShapingProvisions() {
			return shapingProvisions;
		}
		
		@Override
		public SettlementProvision build() {
			return this;
		}
		
		@Override
		public SettlementProvision.SettlementProvisionBuilder toBuilder() {
			SettlementProvision.SettlementProvisionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SettlementProvision.SettlementProvisionBuilder builder) {
			ofNullable(getShapingProvisions()).ifPresent(builder::setShapingProvisions);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementProvision _that = getType().cast(o);
		
			if (!Objects.equals(shapingProvisions, _that.getShapingProvisions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (shapingProvisions != null ? shapingProvisions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementProvision {" +
				"shapingProvisions=" + this.shapingProvisions +
			'}';
		}
	}

	/*********************** Builder Implementation of SettlementProvision  ***********************/
	class SettlementProvisionBuilderImpl implements SettlementProvision.SettlementProvisionBuilder {
	
		protected ShapingProvision.ShapingProvisionBuilder shapingProvisions;
	
		public SettlementProvisionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("shapingProvisions")
		public ShapingProvision.ShapingProvisionBuilder getShapingProvisions() {
			return shapingProvisions;
		}
		
		@Override
		public ShapingProvision.ShapingProvisionBuilder getOrCreateShapingProvisions() {
			ShapingProvision.ShapingProvisionBuilder result;
			if (shapingProvisions!=null) {
				result = shapingProvisions;
			}
			else {
				result = shapingProvisions = ShapingProvision.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("shapingProvisions")
		public SettlementProvision.SettlementProvisionBuilder setShapingProvisions(ShapingProvision shapingProvisions) {
			this.shapingProvisions = shapingProvisions==null?null:shapingProvisions.toBuilder();
			return this;
		}
		
		@Override
		public SettlementProvision build() {
			return new SettlementProvision.SettlementProvisionImpl(this);
		}
		
		@Override
		public SettlementProvision.SettlementProvisionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementProvision.SettlementProvisionBuilder prune() {
			if (shapingProvisions!=null && !shapingProvisions.prune().hasData()) shapingProvisions = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getShapingProvisions()!=null && getShapingProvisions().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementProvision.SettlementProvisionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SettlementProvision.SettlementProvisionBuilder o = (SettlementProvision.SettlementProvisionBuilder) other;
			
			merger.mergeRosetta(getShapingProvisions(), o.getShapingProvisions(), this::setShapingProvisions);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementProvision _that = getType().cast(o);
		
			if (!Objects.equals(shapingProvisions, _that.getShapingProvisions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (shapingProvisions != null ? shapingProvisions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementProvisionBuilder {" +
				"shapingProvisions=" + this.shapingProvisions +
			'}';
		}
	}
}
