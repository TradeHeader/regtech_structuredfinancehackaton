package cdm.product.collateral;

import cdm.product.collateral.CollateralInterestParameters;
import cdm.product.collateral.DistributionAndInterestPayment;
import cdm.product.collateral.DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder;
import cdm.product.collateral.DistributionAndInterestPayment.DistributionAndInterestPaymentBuilderImpl;
import cdm.product.collateral.DistributionAndInterestPayment.DistributionAndInterestPaymentImpl;
import cdm.product.collateral.meta.DistributionAndInterestPaymentMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the Distributions and Interest Payment provisions applicable to the collateral agreement.
 * @version ${project.version}
 */
@RosettaDataType(value="DistributionAndInterestPayment", builder=DistributionAndInterestPayment.DistributionAndInterestPaymentBuilderImpl.class, version="${project.version}")
public interface DistributionAndInterestPayment extends RosettaModelObject {

	DistributionAndInterestPaymentMeta metaData = new DistributionAndInterestPaymentMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the interest parameters for the various currencies, margin types, posting parties.
	 */
	List<? extends CollateralInterestParameters> getInterestParameters();

	/*********************** Build Methods  ***********************/
	DistributionAndInterestPayment build();
	
	DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder toBuilder();
	
	static DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder builder() {
		return new DistributionAndInterestPayment.DistributionAndInterestPaymentBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DistributionAndInterestPayment> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DistributionAndInterestPayment> getType() {
		return DistributionAndInterestPayment.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("interestParameters"), processor, CollateralInterestParameters.class, getInterestParameters());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DistributionAndInterestPaymentBuilder extends DistributionAndInterestPayment, RosettaModelObjectBuilder {
		CollateralInterestParameters.CollateralInterestParametersBuilder getOrCreateInterestParameters(int _index);
		List<? extends CollateralInterestParameters.CollateralInterestParametersBuilder> getInterestParameters();
		DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder addInterestParameters(CollateralInterestParameters interestParameters0);
		DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder addInterestParameters(CollateralInterestParameters interestParameters1, int _idx);
		DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder addInterestParameters(List<? extends CollateralInterestParameters> interestParameters2);
		DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder setInterestParameters(List<? extends CollateralInterestParameters> interestParameters3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("interestParameters"), processor, CollateralInterestParameters.CollateralInterestParametersBuilder.class, getInterestParameters());
		}
		

		DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder prune();
	}

	/*********************** Immutable Implementation of DistributionAndInterestPayment  ***********************/
	class DistributionAndInterestPaymentImpl implements DistributionAndInterestPayment {
		private final List<? extends CollateralInterestParameters> interestParameters;
		
		protected DistributionAndInterestPaymentImpl(DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder builder) {
			this.interestParameters = ofNullable(builder.getInterestParameters()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("interestParameters")
		public List<? extends CollateralInterestParameters> getInterestParameters() {
			return interestParameters;
		}
		
		@Override
		public DistributionAndInterestPayment build() {
			return this;
		}
		
		@Override
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder toBuilder() {
			DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder builder) {
			ofNullable(getInterestParameters()).ifPresent(builder::setInterestParameters);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DistributionAndInterestPayment _that = getType().cast(o);
		
			if (!ListEquals.listEquals(interestParameters, _that.getInterestParameters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestParameters != null ? interestParameters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DistributionAndInterestPayment {" +
				"interestParameters=" + this.interestParameters +
			'}';
		}
	}

	/*********************** Builder Implementation of DistributionAndInterestPayment  ***********************/
	class DistributionAndInterestPaymentBuilderImpl implements DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder {
	
		protected List<CollateralInterestParameters.CollateralInterestParametersBuilder> interestParameters = new ArrayList<>();
	
		public DistributionAndInterestPaymentBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("interestParameters")
		public List<? extends CollateralInterestParameters.CollateralInterestParametersBuilder> getInterestParameters() {
			return interestParameters;
		}
		
		public CollateralInterestParameters.CollateralInterestParametersBuilder getOrCreateInterestParameters(int _index) {
		
			if (interestParameters==null) {
				this.interestParameters = new ArrayList<>();
			}
			CollateralInterestParameters.CollateralInterestParametersBuilder result;
			return getIndex(interestParameters, _index, () -> {
						CollateralInterestParameters.CollateralInterestParametersBuilder newInterestParameters = CollateralInterestParameters.builder();
						return newInterestParameters;
					});
		}
		
	
		@Override
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder addInterestParameters(CollateralInterestParameters interestParameters) {
			if (interestParameters!=null) this.interestParameters.add(interestParameters.toBuilder());
			return this;
		}
		
		@Override
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder addInterestParameters(CollateralInterestParameters interestParameters, int _idx) {
			getIndex(this.interestParameters, _idx, () -> interestParameters.toBuilder());
			return this;
		}
		@Override 
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder addInterestParameters(List<? extends CollateralInterestParameters> interestParameterss) {
			if (interestParameterss != null) {
				for (CollateralInterestParameters toAdd : interestParameterss) {
					this.interestParameters.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("interestParameters")
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder setInterestParameters(List<? extends CollateralInterestParameters> interestParameterss) {
			if (interestParameterss == null)  {
				this.interestParameters = new ArrayList<>();
			}
			else {
				this.interestParameters = interestParameterss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public DistributionAndInterestPayment build() {
			return new DistributionAndInterestPayment.DistributionAndInterestPaymentImpl(this);
		}
		
		@Override
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder prune() {
			interestParameters = interestParameters.stream().filter(b->b!=null).<CollateralInterestParameters.CollateralInterestParametersBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInterestParameters()!=null && getInterestParameters().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder o = (DistributionAndInterestPayment.DistributionAndInterestPaymentBuilder) other;
			
			merger.mergeRosetta(getInterestParameters(), o.getInterestParameters(), this::getOrCreateInterestParameters);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DistributionAndInterestPayment _that = getType().cast(o);
		
			if (!ListEquals.listEquals(interestParameters, _that.getInterestParameters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestParameters != null ? interestParameters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DistributionAndInterestPaymentBuilder {" +
				"interestParameters=" + this.interestParameters +
			'}';
		}
	}
}
